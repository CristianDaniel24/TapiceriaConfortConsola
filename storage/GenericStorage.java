package Proyecto.TapiceriaConfort.storage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class GenericStorage<T extends Storable> {
    private final String storagePath;

    public GenericStorage(String storagePath) {
        this.storagePath = storagePath;
    }

    public void save(T item) {
        try (FileWriter writer = new FileWriter(this.storagePath, true)) {
            writer.write(item.serialize() + System.lineSeparator());
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<T> load(T prototype) {
        try {
            List<T> items = new LinkedList<>();
            BufferedReader reader = new BufferedReader(new FileReader(this.storagePath));
            String line;
            while ((line = reader.readLine()) != null) {
                items.add((T) prototype.deserialize(line));
            }
            reader.close();
            return items;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
