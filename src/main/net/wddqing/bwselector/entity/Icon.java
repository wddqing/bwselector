package net.wddqing.bwselector.entity;

/**
 * Created by Administrator on 2016/10/22.
 */
public class Icon {




    private String command;

    public int getId() {
        return id;
    }

    private int id;

    public String getItem() {
        return item;
    }

    private String item;


    private String lore;

    public int getAmount() {
        return amount;
    }

    private int amount;

    public Icon(String command, int id, String item, String lore, int amount) {
        this.command = command;
        this.id = id;
        this.item = item;
        this.lore = lore;
        this.amount = amount;
    }
}
