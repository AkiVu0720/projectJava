package interFace;

import java.util.List;
import java.util.Scanner;

public interface IEntity<T> {
    void input(Scanner scanner, List<T>arrayList);
    void output();
}
