package com.elmenus.task.springboottask.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Restaurant {

    @NotBlank(message = "uuid must be provided")
    String uuid;

    @NotNull(message = "data must be provided")
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
