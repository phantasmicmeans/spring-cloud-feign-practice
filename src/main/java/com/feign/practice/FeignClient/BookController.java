package com.feign.practice.FeignClient;

import com.feign.practice.domain.Book;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.netflix.discovery.shared.Applications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController implements BookClient{

    //@Autowired
    ///private DiscoveryClient discoveryClient;

    @Autowired
    private EurekaClient eurekaClient;

    @Override
    public String getDataEureka() { //application 세부 정보

        Application applications = eurekaClient.getApplication("Feign-Client");
        return applications.toString();
    }

    @Override
    public String getApplicationsFromEureka() { //등록된 application.name 전체 가져오기(ex. Fegin-Client)

        Optional<Applications> applications = Optional.of(eurekaClient.getApplications());
        List<Application> applicationLst = applications.get().getRegisteredApplications();
        StringBuilder sb = new StringBuilder();
        applicationLst.forEach((application) -> sb.append(application.getName()));
        return sb.toString();
    }

    @Override
    public String getDataFromEureka(String instanceId) {

        Optional<List<InstanceInfo>> instanceInfo
                = Optional.ofNullable(eurekaClient.getInstancesById(instanceId));

        List<InstanceInfo> info = instanceInfo.orElseThrow(()-> new NullPointerException("cannot found instacne with instanceId"));

        StringBuilder sb = new StringBuilder();
        info.forEach((instances) -> sb.append(instances.toString()));

        return sb.toString();
    }

    @Override
    public List<Book> getBooks() {
        return null;
    }

    @Override
    public String getBookSpec() {
        return "bookSpec";
    }

    @Override
    public Book update(Long bookId) {
        return null;
    }
}
