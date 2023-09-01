

export interface Response {
    id: number,
    agentId: number,
    customerId: number,
    message: string,
    ticketStatus: string,
    createdTimestamp: string,
    responseByAgent: boolean
}