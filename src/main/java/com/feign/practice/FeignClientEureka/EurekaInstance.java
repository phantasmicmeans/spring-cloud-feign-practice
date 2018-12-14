package com.feign.practice.FeignClientEureka;

import com.netflix.appinfo.InstanceInfo;

import java.util.List;

public class EurekaInstance{
    /*Application 와, InstanceInfo 뒤져서 toString 다시 완성 시키셈...
       Application의 instance ( = InstanceInfo에 있음
        */
    private List<InstanceInfo> eurekaInstances;
    private StringBuilder sb;

    public EurekaInstance() { }

    public void setEurekaInstances(List<InstanceInfo> eurekaInstances) {
        this.eurekaInstances = eurekaInstances;
    }

    public StringBuilder simpleInstanceInfo(){

        sb = new StringBuilder();
        this.eurekaInstances.forEach((instance) -> sb.append(simpleToString(instance)));
        return sb;
    }
    public StringBuilder InstancesInfo() {

        sb = new StringBuilder();
        this.eurekaInstances.forEach((instance) -> sb.append(toString(instance)));
        return sb;
    }

    public String simpleToString(InstanceInfo i){

        return ("AppName = [" + i.getAppName() + "]" + "\n" +
                "HostName = " + i.getHostName() + "]" + "\n" +
                "Id = " + i.getId() + "]" + "\n" +
                "IpAddr = " + i.getIPAddr() + "]" + "\n" +
                "Status = " + i.getStatus()) + "]" + "\n\n";

    }

    public String toString(InstanceInfo i) {
        return "============ Application Detail ==============\n" +
                "InstanceId: [" + i.getInstanceId() + "]" + "\n" +
                "AppName : [" + i.getAppName() + "]" + "\n" +
                "AppGroupName : [" + i.getAppGroupName() + "]" + "\n" +
                "HostName: [" + i.getHostName() + "]" + "\n" +
                "Id : [" + i.getId() + "]" + "\n" +
                "IpAddr : [" + i.getIPAddr() + "]" + "\n" +
                "Port : [" + i.getPort() + "]" + "\n" +
                "Status : [" + i.getStatus() + "]" + "\n" +
                "DataCenterInfo : [" + i.getDataCenterInfo() + "]" + "\n" +
                "MetaData : [" + i.getMetadata() + "]" + "\n" +
                "LastUpdateTimestamp : [" + i.getLastUpdatedTimestamp() + "]" + "\n" +
                "HealthCheckURL : [" + i.getHealthCheckUrl() + "]" + "\n\n";
    }
}

