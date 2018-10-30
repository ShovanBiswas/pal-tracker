package io.pivotal.pal.tracker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    Map<Long, TimeEntry> timeEntries = new HashMap<>();

    public TimeEntry create(TimeEntry timeEntry) {
        timeEntry = new TimeEntry((timeEntries.size() + 1L), timeEntry);
        this.timeEntries.put(timeEntry.getId(), timeEntry);
        return timeEntry;
    }

    public TimeEntry find(long id) {
        return timeEntries.get(id);
    }

    public List<TimeEntry> list() {
        return this.timeEntries.values().stream()
                .collect(Collectors.toList());
    }

    public TimeEntry update(long id, TimeEntry timeEntry) {
        TimeEntry t = timeEntries.get(id);
        if (t != null) {
            timeEntries.remove(id);
            t = new TimeEntry(id, timeEntry);
            timeEntries.put(id, t);
            return t;
        }
        return null;
    }

    public TimeEntry delete(long id) {

        return timeEntries.remove(id);
    }
}
