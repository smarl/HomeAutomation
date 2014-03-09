<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ha" uri="http://homeautomation.brokenneon.com/functions" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <title>Our House</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
  
	<link rel="apple-touch-icon-precomposed" href="img/icon.png"/>
    <link rel="stylesheet" type="text/css" href="bootstrap-3.0.3/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="bootstrap-3.0.3/css/bootstrap-theme.min.css">
    <link rel="stylesheet" type="text/css" href="css/styles.css">
    
    <script type="text/javascript" src="js/backbone-min.js"></script>
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="bootstrap-3.0.3/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/app.js"></script>
  </head>

  <body>
    <c:set value="${ha:getHouse()}" var="house" />
    <c:if test="${house.online}">
    	<div class="levels progress" level="${house.averageLevel}" dest="house" actionableId="${house.actionableId}">
    		<div class="progress-bar" role="progressbar" aria-valuenow="${house.averageLevel}" aria-valuemin="0" aria-valuemax="100" style="width:${house.averageLevel}%;">Whole&nbsp;house<span class="levelLabel"></span></div>
    	</div>
    </c:if>
	<c:if test="${not house.online}">House offline!</c:if>

  	<ul class="list-group">
      <c:forEach items="${house.rooms}" var="room">
        <li class="list-group-item room_outer">
          <c:if test="${room.online}">
            <button type="button" class="btn btn-xs showRoom pull-left"><span class="glyphicon glyphicon-chevron-down"></span></button>
            <div class="levels room progress" level="${room.averageLevel}" dest="room" actionableId="${room.actionableId}">
              <div class="progress-bar" role="progressbar" aria-valuenow="${room.averageLevel}" aria-valuemin="0" aria-valuemax="100" style="width:${room.averageLevel}%;">${fn:replace(room.name," ","&nbsp;")}<span class="levelLabel"></span></div>
            </div>
          </c:if>
          <c:if test="${not room.online}">${room.name} offline!</c:if>

          <ul class="list-group notshowing">
            <c:forEach items="${room.devices}" var="device">
              <li class="list-group-item device_outer">
                <c:if test="${device.online}">
                  <div class="levels device progress" level="${device.level}" dest="did" actionableId="${device.actionableId}">
                    <div class="progress-bar" role="progressbar" aria-valuenow="${device.level}" aria-valuemin="0" aria-valuemax="100" style="width:${device.level}%;">${fn:replace(device.name," ","&nbsp;")}<span class="levelLabel"></span></div>
                  </div>
                </c:if>
                <c:if test="${not device.online}">${device.name} offline.</c:if>
              </li>
            </c:forEach>
          </ul>

        </li>
      </c:forEach>
    </ul>
  </body>
</html>
