javac  -d . subif.java
javac  -d . subifimpl.java
rmic submits.subifimpl
start rmiregistry
java submits.subifimpl
