package com.elmenus.task.springboottask.models;

import javax.validation.constraints.NotBlank;

public class Restaurant {

    @NotBlank(message = "uuid must be provided")
    String uuid;
    Data data;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
