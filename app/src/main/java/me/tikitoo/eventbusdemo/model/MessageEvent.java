package me.tikitoo.eventbusdemo.model;

public class MessageEvent {
    private String id;
    private String name;
    private String age;

    public MessageEvent(String id, String name, String age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public String getId() {
        return "id: " + id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return "name: " + name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return "age: " + age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
