package net.wddqing.bwselector.config;

/**
 * Created by Administrator on 2016/10/22.
 */
public class Node {

    public String getName() {
        return name;
    }

    protected String name;

    public String getAddr() {
        return addr;
    }

    protected String addr;

    public Node(String name, String addr) {
        this.name = name;
        this.addr = addr;
    }
}
