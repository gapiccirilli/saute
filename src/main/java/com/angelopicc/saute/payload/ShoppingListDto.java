package com.angelopicc.saute.payload;


public class ShoppingListDto {
    
    private long id;
    private String listName;

    private final String type = "Shopping List";
    
    public ShoppingListDto() {
    }

    public ShoppingListDto(long id, String listName) {
        this.id = id;
        this.listName = listName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }
    
    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "ShoppingListDto [id=" + id + ", listName=" + listName + "]";
    }
}
