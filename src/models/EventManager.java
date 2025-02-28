package models;

import java.util.ArrayList;
import java.util.List;

public class EventManager {
    private static EventManager instance;
    private List<Event> eventsList;

    private EventManager() {
        eventsList = new ArrayList<>();
    }

    public static EventManager getInstance() {
        if (instance == null) {
            instance = new EventManager();
        }
        return instance;
    }

    public List<Event> getAllEvents() {
        return eventsList;
    }

    public void addEvent(Event event) {
        eventsList.add(event);
    }

    public void removeEvent(int index) {
        if (index >= 0 && index < eventsList.size()) {
            eventsList.remove(index);
        }
    }

    // เพิ่มฟังก์ชัน updateEvent
    public void updateEvent(int index, Event updatedEvent) {
        if (index >= 0 && index < eventsList.size()) {
            eventsList.set(index, updatedEvent);
        }
    }
}