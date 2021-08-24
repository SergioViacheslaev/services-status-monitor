const greenStatusImg = '<img width="100%" height="100%" src="../../img/greenStatus.png"/>'
const redStatusImg = '<img width="100%" height="100%" src="../../img/redStatus.png"/>'

function setConnected(connected) {
    if (connected) {
        $("#servicesListTable").show();
    } else {
        $("#servicesListTable").hide();
    }
}

function connect() {
    const loc = window.location;
    const url = '//' + loc.host + loc.pathname + '/websocket';

    const stompClient = Stomp.over(new SockJS(url));
    stompClient.connect({}, frame => {

        setConnected(true);
        console.log(`Connected: ${frame}`);

        stompClient.subscribe('/topic/servicesStatusInfo', serviceStatusInfo =>
            updateServicesInfoTable(JSON.parse(serviceStatusInfo.body)));
    });
}

const updateServicesInfoTable = (monitoredService) => {
    let serviceStatusDataRow = '';
    serviceStatusDataRow += '<tr>';
    if (monitoredService.shortStatus === "UP") {
        serviceStatusDataRow += '<td>' + greenStatusImg + '</td>';
    } else if (monitoredService.shortStatus === "DOWN" || monitoredService.shortStatus === "OFFLINE") {
        serviceStatusDataRow += '<td>' + redStatusImg + '</td>';
    } else {
        serviceStatusDataRow += '<td></td>';
    }
    serviceStatusDataRow += '<td>' + monitoredService.serviceName + '</td>';
    serviceStatusDataRow += '<td>' + monitoredService.shortStatus + '</td>';
    serviceStatusDataRow += '<td>' + monitoredService.usedMemoryPercentage + '</td>';
    serviceStatusDataRow += '<td>' + monitoredService.fullStatus + '</td>';
    serviceStatusDataRow += '</tr>'


    function isServiceDataUpdated() {
        let serviceRowExists = false;
        $("#servicesListTable tr").each(
            function () {
                const currentRow = $(this);
                const serviceNameColumnText = currentRow.find("td:eq(1)").text();
                if (serviceNameColumnText === monitoredService.serviceName) {
                    currentRow.replaceWith(serviceStatusDataRow)
                    return serviceRowExists = true;
                }
            }
        );
        return serviceRowExists;
    }

    if (!isServiceDataUpdated()) {
        $("#servicesListTable").append(serviceStatusDataRow);
    }


};

$(document).ready(function () {
    connect()
});

