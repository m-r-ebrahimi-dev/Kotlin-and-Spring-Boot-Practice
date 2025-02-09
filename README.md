# Trailing Lambdas in Kotlin vs Java

## What is a Trailing Lambda?

In Kotlin, if the last parameter of a function is a **lambda expression**, you can place it **outside the parentheses**
to improve code readability. This is called a **Trailing Lambda**.

### Example in Kotlin:

```kotlin
fun greet(name: String, action: () -> Unit) {
    println("Hello, $name!")
    action()
}

// Without Trailing Lambda
 greet("Alice", { println("Welcome!") })

// With Trailing Lambda (More readable)
greet("Alice") {
    println("Welcome!")
}
```

## Comparison with Java

Before Java 8, we had to use **Anonymous Classes**, making the code very verbose:

### ✅ **Java (Before Java 8 - Anonymous Class)**

```java
public class Main {
    public static void main(String[] args) {
        greet("Alice", new Runnable() {
            @Override
            public void run() {
                System.out.println("Welcome!");
            }
        });
    }

    static void greet(String name, Runnable action) {
        System.out.println("Hello, " + name + "!");
        action.run();
    }
}
```

### ✅ **Java (After Java 8 - Lambda Expressions)**

```java
public class Main {
    public static void main(String[] args) {
        greet("Alice", () -> System.out.println("Welcome!"));
    }

    static void greet(String name, Runnable action) {
        System.out.println("Hello, " + name + "!");
        action.run();
    }
}
```

### ✅ **Kotlin (Trailing Lambda)**

```kotlin
fun greet(name: String, action: () -> Unit) {
    println("Hello, $name!")
    action()
}

fun main() {
    greet("Alice") {
        println("Welcome!")
    }
}
```

## Example with `forEach`

### ✅ **Java**

```java
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");

        // Old-style loop
        for (String name : names) {
            System.out.println(name);
        }

        // Java 8+ Lambda
        names.forEach(name -> System.out.println(name));
    }
}
```

### ✅ **Kotlin**

```kotlin
val names = listOf("Alice", "Bob", "Charlie")

// More readable with Trailing Lambda
names.forEach { name ->
    println(name)
}
```

## Higher-Order Function Example

### ✅ **Java**

```java
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        doSomething(() -> System.out.println("Doing something..."));
    }

    static void doSomething(Runnable action) {
        action.run();
    }
}
```

### ✅ **Kotlin**

```kotlin
fun doSomething(action: () -> Unit) {
    action()
}

fun main() {
    doSomething {
        println("Doing something...")
    }
}
```

## Summary: Kotlin vs Java

| Feature           | Java                                              | Kotlin                                |
|-------------------|---------------------------------------------------|---------------------------------------|
| **Before Java 8** | Anonymous Class (Verbose)                         | -                                     |
| **After Java 8**  | Lambda (Inside Parentheses)                       | Trailing Lambda (Outside Parentheses) |
| **ForEach Usage** | `list.forEach(name -> System.out.println(name));` | `list.forEach { println(it) }`        |
| **Cleaner Code?** | ❌ No (Requires Parentheses)                       | ✅ Yes (More Readable)                 |

### Conclusion:

✅ **Kotlin** with **Trailing Lambdas** makes code **shorter, cleaner, and more readable** than Java! 🚀

