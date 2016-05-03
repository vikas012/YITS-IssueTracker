<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<ul class="nav navbar-right top-nav">
					      
					     
						  <li><a><span class="fa fa-search" ></span> <input type="text" placeholder="Search Project Name,task......"/></a></li>
					      <li><a href="#"><span class="fa fa-print" ></span></a></li>
					      <li><a href="#"><span class="fa fa-fw fa-wrench" ></span></a></li>
					      <li><a href="#"><span class="fa fa-list" ></span></a></li>
                          <li class="dropdown" ><a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true" ><img src='<spring:url value="/images/user.png"></spring:url>' class="img-circle" alt="Cinque Terre" width="20" height="20"> </a>
                               <ul class="dropdown-menu" >
                                    
                                    <li> <img src='<spring:url value="/images/user.png"></spring:url>' class="img-circle" alt="Cinque Terre" width="70" height="75"> user Name</li>
                                    <li class="divider"></li>
                                    <li><a href="#"><i class="fa fa-lock" aria-hidden="true"></i> LogOut</a></li>
                              </ul>
						  </li>
							
                </ul>