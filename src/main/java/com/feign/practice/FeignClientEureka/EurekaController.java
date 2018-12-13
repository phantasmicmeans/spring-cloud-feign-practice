package com.feign.practice.FeignClientEureka;

import com.feign.practice.ResourceNotFoundException;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.netflix.discovery.shared.Applications;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ValidationException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
public class EurekaController implements ClientEureka {

    @Autowired
    private EurekaClient eurekaClient;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${spring.application.name}")
    private String clientName;

    @Value("${eureka.instance.instance-id")
    private String instanceId;

    @Override
    public ResponseEntity getDetailFromEureka() { //(x - getInstanceList로 부터 오류 )

        StringBuilder sb = getInstanceList(this.instanceId);
        return !sb.toString().equals("empty")?
                ResponseEntity.notFound().build() : ResponseEntity.ok(sb.toString());
    }

    @Override
    public ResponseEntity getApplicationsFromEureka() { //(o)
        //resoureNotFoundException is custom Exception with HttpStatus 404
        List<Application> applications = Optional.ofNullable(eurekaClient.getApplications())
                                    .orElseThrow(() -> new ResourceNotFoundException("No data")) //Http 404
                                    .getRegisteredApplications();
        StringBuilder sb = new StringBuilder();
        applications.forEach(sb::append);
        return ResponseEntity.ok(sb.toString());
    }

    @Override
    public ResponseEntity getApplicationWithApplicationName(String applicationName) { //(o)

        if(checkPathVariable(applicationName)) return ResponseEntity.badRequest().body(new ValidationException("Data invalid"));

        Application application = Optional.ofNullable(eurekaClient.getApplication(applicationName))
                                                    .orElseThrow(()-> new ResourceNotFoundException("No Applications with that name"));
        return ResponseEntity.ok(application.toString());
    }

    @Override
    public ResponseEntity getInstanceDetailFromEureka(String instanceId) { //(x)

        if(checkPathVariable(instanceId)) return ResponseEntity.badRequest().body(new ValidationException("Data invalid"));

        StringBuilder sb = getInstanceList(instanceId);
        return !sb.toString().equals("empty")?
                ResponseEntity.notFound().build() : ResponseEntity.ok(sb.toString());
    }

    @Override
    public ResponseEntity getAnyRegionsFromEureka() { // (o)

        Set<String> regions = Optional.of(eurekaClient.getAllKnownRegions())
                                    .orElseThrow(() -> new ResourceNotFoundException("No Regions"));

        StringBuilder sb = new StringBuilder();
        regions.forEach(sb::append);
        return ResponseEntity.ok(sb.toString());
    }

    @Override
    public ResponseEntity getApplicationsForRegions(String regions) { //(o)

        if(checkPathVariable(regions)) return ResponseEntity.badRequest().body("Data invalid");

        Applications application = Optional.ofNullable(eurekaClient.getApplicationsForARegion(regions))
                                    .orElseThrow(() -> new ResourceNotFoundException("No Application with that regions"));

        StringBuilder sb = new StringBuilder();
        application.getRegisteredApplications().forEach(sb::append);
        return ResponseEntity.ok(sb.toString());
    }

    private StringBuilder getInstanceList(String instanceId){ //(x getInstanceById 뜯어봐야 할듯 )

        logger.info("ipt instanceId = " + instanceId);
        List list = eurekaClient.getInstancesById(instanceId);
        if(list.isEmpty())
            return new StringBuilder("empty");

        StringBuilder sb = new StringBuilder();
        //list.forEach(e -> sb.append(toString()));
        //list.forEach((instanceInfo) -> sb.append(getAppender(instanceInfo.) + "\n\n"));
        logger.info("instance detail = " + sb.toString());
        return sb;
    }
    private String getAppender(InstanceInfo instanceInfo){

        return ("AppName = " + instanceInfo.getAppName() + "\n" +
                "HostName = " + instanceInfo.getHostName() + "\n" +
                "Id = " + instanceInfo.getId() + "\n" +
                "IpAddr = " + instanceInfo.getIPAddr() + "\n" +
                "Status = " + instanceInfo.getStatus());
    }

    private boolean checkPathVariable(String str){
        return (str.isEmpty() || str.length() < 3);
    }
}
