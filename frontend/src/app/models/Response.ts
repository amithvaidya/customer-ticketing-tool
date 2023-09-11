

export interface Response {
    id: number,
    agentId: number,
    agentName: string,
    customerId: number,
    message: string,
    ticketStatus: string,
    createdTimestamp: string,
    responseByAgent: boolean,
    customerName: string,
    companyName: string
}