Это локализация, работающая со строками, компонентами и легаси строками.

Примеры можно посмотреть в тестах.

Зависимости:

- adventure-text-serializer-gson (`provided`)
- adventure-api (`provided`)
- adventure-text-minimessage (`provided`)
- adventure-text-serializer-plain (`provided`)
- adventure-text-serializer-legacy (`provided`)
- gson (`compile`)
- и другие...

Чтобы добавить основную часть библиотеки (если вам нужен bungeecord, то нужно добавить ещё localization-bungee):

`dependency:`

```xml
<dependency>
  <groupId>net.laffeymyth</groupId>
  <artifactId>localization-commons</artifactId>
  <version>1.0.10</version>
</dependency>
```

`repository:`

```xml
<repository>
    <id>javaplugg</id>
    <url>https://repo.javaplugg.net/releases/</url>
</repository>
```

gradle (groovy):

`dependency:`

```groovy
implementation "net.laffeymyth:localization-commons:1.0.10"
```

`repository:`

```groovy
maven {
    url = uri("https://repo.javaplugg.net/releases/")
}
```

kotlin (kotlin):

`dependency:`

```kotlin
implementation("net.laffeymyth:localization-commons:1.0.10")
```

`repository:`

```kotlin
maven {
    url = uri("https://repo.javaplugg.net/releases/")
}
```