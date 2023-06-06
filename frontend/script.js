
let components = ['agents', 'tickets', 'customers'];

function displaySection(componentName){
    for(let i=0; i<components.length; i++) {
        document.getElementById(components[i]+"Section").style.display = 'none';
        document.getElementById(components[i]+"Button").className = 'btn btn-light';
    }
    
    if(componentName != '') {
        document.getElementById(componentName+"Section").style.display = 'block';
        document.getElementById(componentName+"Button").className = 'btn btn-primary';
    }
}


let baseURL = "http://localhost:8080";
let agents = {};
function getAgents(){
    let request = new XMLHttpRequest();
    let url = baseURL+"/admin/agents?for_date="+document.getElementById('forDate').value;
    request.open("GET", url);
    request.send();
    request.onload = () => {
        console.log(request);
        if(request.status == 200) {
            agents = JSON.parse(request.response);
        }
        else 
            console.log(`Error: ${request.status} ${request.statusText} `);
    }
}


function displayAgents(){
    
    let htmlCode = "<table class='table table-stripped'>"+
        "<tr>"+
        "<th>Agent Name</th>"+
        "<th>No. of tickets assigned</th>"+
        "<th>Average Response Time</th>"+
        "<th>Average Resolution Time</th>"+
        "<th>Average Customer Rating</th>"+
        "</tr>";
    for(let i=0; i<agents.length; i++){
        htmlCode += 
            "<tr>"+
            "<td>"+agents[i].name+"</td>"+
            "<td>"+agents[i].numberOfTicketsAssigned+"</td>"+
            "<td>"+agents[i].avgResponseTime+"</td>"+
            "<td>"+agents[i].avgResolutionTime+"</td>"+
            "<td>"+agents[i].avgCustomerRating+"</td>"+
            "</tr>";
    }

    htmlCode+="</table>";

    document.getElementById('agentsList').innerHTML = htmlCode;
}