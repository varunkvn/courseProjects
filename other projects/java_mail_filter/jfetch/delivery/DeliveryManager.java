package jfetch.delivery;

import java.util.HashMap;

import org.apache.avalon.Component;

/**
 * The DeliveryManager stores the registry of all Delivery Agents.
 * It provides access to these agents to all the filters.
 *
 * 
 */
public class DeliveryManager {
    private HashMap hash = new HashMap();
    private static DeliveryManager instance = null;

    private DeliveryManager() {
    }

    public static synchronized DeliveryManager getInstance() {
        if(instance == null) {
            instance = new DeliveryManager();
        }
        return instance;
    }

    /**
     * Add a delivery agent to the registry
     *
     * @param da a <code>DeliveryAgent</code> value
     * @throws IllegalArgumentException An agent already exists with the same id
     */
    public void addAgent(DeliveryAgent da) {
        if(hash.containsKey(da.getId())) {
            throw  new IllegalArgumentException("Duplicate id");
        }
        hash.put(da.getId(), da);
    }

    /**
     * Get the Agent whose id matches the given one
     *
     * @param id id of the Agent who is requested for
     * @return The DeliveryAgent if it exists, else null
     */
    public DeliveryAgent getAgent(String id) {
        return (DeliveryAgent) hash.get(id);
    }
}
