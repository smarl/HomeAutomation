<%@ page contentType="text/html;charset=UTF-8" %>
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
    	<div class="btn-group btn-group">
    		<c:forEach items="${house.levelDisplay}" var="level">
    			<c:set value="" var="extraClass"></c:set>
    			<c:if test='${level.active}'>
    				<c:set value="btn-default" var="extraClass"></c:set>
    			</c:if>
    			<button type="button" class="btn house ${extraClass}" level="${level.level}" house="1">${level.display}</button>
    		</c:forEach>
    	</div>
    </c:if>
	<c:if test="${not house.online}">House offline!</c:if>

  	<ul class="list-group">
    	<c:forEach items="${house.rooms}" var="room">
    		<li class="list-group-item room_outer">
    			<div>${room.name}</div>
    			<c:if test="${room.online}">
    				<div class="btn-group btn-group">
    					<c:forEach items="${room.levelDisplay}" var="level">
    						<c:set value="" var="extraClass"></c:set>
    						<c:if test='${level.active}'>
    							<c:set value="btn-default" var="extraClass"></c:set>
    						</c:if>
    						<button type="button" class="btn room ${extraClass}" level="${level.level}" room="${room.name}">${level.display}</button>
    					</c:forEach>
    				</div>
	    			<button type="button" class="btn showRoom"><span class="glyphicon glyphicon-chevron-down"></span></button>
    			</c:if>
    			<c:if test="${not room.online}">Room offline!</c:if>
    			<ul class="list-group notshowing">
			    	<c:forEach items="${room.devices}" var="device">
    					<li class="list-group-item">
	    					<div>${device.name}</div>
    						<c:if test="${device.online}">
				    			<div class="btn-group btn-group">
				    				<c:forEach items="${device.levelDisplay}" var="level">
				    					<c:set value="" var="extraClass"></c:set>
										<c:if test='${level.active}'>
											<c:set value="btn-default" var="extraClass"></c:set>
										</c:if>
		  				  				<button type="button" class="btn device ${extraClass}" level="${level.level}" did="${device.did}">${level.display}</button>
		  				  			</c:forEach>
    							</div>
    						</c:if>
    						<c:if test="${not device.online}">Device offline.</c:if>
    					</li>
			    	</c:forEach>
    			</ul>
    		</li>
    	</c:forEach>
  	</ul>
  </body>
</html>
