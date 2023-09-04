
export interface Ticket {
    id: number,
    title: string,
    agentId: number, // not required
    agentName: string,
    customerId: number, //Not required
    customerName: string,
    companyName: string
    createdTimestamp: string,
    status: string,
    priority: number,
    priorityLabel: string
}