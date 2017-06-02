<%@taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jstl/sql" prefix="sql" %>


<sql:setDataSource var="ds" 
      driver="sun.jdbc.odbc.JdbcOdbcDriver"
      url="jdbc:odbc:oraclecon"
      user="swathi" 
      password="swathi53"/>
<sql:update dataSource="${ds}">
insert into register values(?,?,?,?,?,?,?,?,?)

<sql:param value="${param.sname}" />
<sql:param value="${param.username}" />
<sql:param value="${param.password}" />
<sql:param value="${param.preCourse}" />
<sql:param value="${param.joinCourse}" />
<sql:param value="${param.age}" />
<sql:param value="${param.phone}" />
<sql:param value="${param.address}" />
<sql:param value="${param.percentage}" />
</sql:update>

<h2>Congratulations !! .,. you are now registered to write an online examination. please  go back and login with your details to write the teset</h2>