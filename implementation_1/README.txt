	 -------------------------
	| Customer ticketing tool |
 	 -------------------------

Summary:
This is a CRM tool that allows a business's customer raise query/issue tickets which can be monitored by the business. Here there are 3 concepts: Admin, Agent and Customer.


Functionalities:

1) Admin

	-Can get the performance of an agent on a day to day basis
	-Can get information about the customers
	-Can create tickets and assign to Agents
	-Can create a new Agent profile
	-Can filter and view all the tickets received
	-Can comment on the tickets
	-Can set configuration like: SLA metrics, Ticket categories, statuses, how they are assigned to agents

2) Agent
	-Can view list of tickets assigned to this agent
	-Can filter out tickets based on certain parameters
	-Can respond to the tickets, update status
	-Can create tickets
	-Can handover tickets to other agent
	-Mark status if available or not

3) Customer
	-Can view all tickets raised by him/her
	-Can create ticket, respond and send feedback rating


Definition of Agent metrics:

1) Average Response Time:

Average Response Time for a ticket is the average of time duration between a customer's response and the immediate after response of the agent.
Average Response Time of an agent is the average of ART of all the tickets handled by him.
Overall Average Response Time of the support team is the average of ART of all the agents.
This can be calculated on a date basis (Average Response Time for each agent for a particular date).


2) Average Resolution Time:
The same concept of ART explained above, only change is the context of time is taken from resolution.

3) Agent CSAT
Average of the csat_rating for all tickets handled by the agent and where the customer has provided the rating. Note you must exclude the tickets where customer has not given the rating.


Tech Stack:

Version 1:
	-No frameworks like spring is used, just JDBC, Servlets and JSP
	-Create a seperate package for Servlets/JSP and other functionalities
	-Just use notepad, maven and apache tomcat to write and execute the code
Version 2:
	-Upgrade to Spring Framework
	-Use Spring MVC and Spring JDBC APIs
	-Use JSP to display the frontend
Version 3:
	-Move to Spring boot
	-For frontend, use Angular framework





