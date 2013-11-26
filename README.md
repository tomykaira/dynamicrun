# How to run

Unix:

    ./gradlew run

Windows (maybe, never tested):

    .\gradlew.bat run

# Mechanism

- Using `ToolProvider.getSystemJavaCompiler()` to compile a string into java class.
- Using `Files.createTempDirectory()` to create the temporary destination directory.
- Using `URLClassLoader` to load a compiled class from the temporary directory.

# References

[javax.tools パッケージを使って Java コードをダイナミックにコンパイル！ - 倭マン's BLOG](http://waman.hatenablog.com/entry/20110310/1299721161)
[file - Create a temporary directory in Java - Stack Overflow](http://stackoverflow.com/questions/617414/create-a-temporary-directory-in-java)
[URLClassLoader (Java Platform SE 6)](http://docs.oracle.com/javase/jp/6/api/java/net/URLClassLoader.html)
[Is it possible to "add" to classpath dynamically in java? - Stack Overflow](http://stackoverflow.com/questions/402330/is-it-possible-to-add-to-classpath-dynamically-in-java)

# License

Public license.  You can use any code freely.
