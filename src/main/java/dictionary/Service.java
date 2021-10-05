/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionary;

/**
 *
 * @author beatl
 */
public class Service {
    private String name;
    
    private long factor;

    public Service(String name, long factor) {
        this.name = name;
        this.factor = factor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getFactor() {
        return factor;
    }

    public void setFactor(long factor) {
        this.factor = factor;
    }

    @Override
    public String toString() {
        return "Service{" + "name=" + name + ", factor=" + factor + '}';
    }

    
}
