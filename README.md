[![Badge Icon](https://img.shields.io/maven-central/v/org.crumbleworks.forge.crumbprops/CrumbProps?style=flat-square)](https://mvnrepository.com/artifact/org.crumbleworks.forge.crumbprops/CrumbProps)

# CrumbProps

A library to ease using property files.

### Java Version

The library is designed to work against Java8. And will stay that way for the forseeable future.

### Why?

Because property files are nice, but using them is a pain. Thus I wrote this tool some 10 years ago.

## How to use

1. Create a POJO representing your property file.
```
public class UserSettings {

    public Integer favouriteNumber;
    
    public Integer secondFavouriteNumber;
    
    public String bestLanguage;
}
```

2. Annotate it
```
@PropertyFile(fileName = user.conf)
public class UserSettings {

    @Property(key = "favourite_number")
    public Integer favouriteNumber;
    
    @Property(key = "second_favourite_number")
    public Integer secondFavouriteNumber;
    
    @Property(key = "best_linguine", defaultValue = "de-CH")
    public String bestLanguage;
}
```

3. Load/Save/Whatever you need
```
UserSettings userSettings = new UserSettings();
PropertyManager pm = new PropertyManager();

//Add settings to a PropertyManager
pm.add(userSettings);

//Load settings from file
pm.load(userSettings);

//Save settings to file
pm.save(userSettings);
```

## Development

### Contributors

* [Michael "dot_Sp0T" Stocker](https://github.com/dotSp0T)
* [Patrick "McDonnough" Bächli](https://github.com/McDonnough)

### Report Bugs
If you happen upon bugs and/or behaviour you think is wrong, please report a bug under the [Issue-Tracker](https://github.com/CrumbleWorks/CrumbProps/issues).

### Toolchain

- Java11 (or newer): https://jdk.java.net/java-se-ri/11
- Maven: https://maven.apache.org/

### How to contribute

Write code. Make a PR. Explain Why.

#### Eclipse Setup

run `mvn clean eclipse:eclipse -DdownloadSources -DdownloadJavadoc`
