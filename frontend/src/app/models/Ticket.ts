
export interface Ticket {
    id: number,
    title: string,
    agentId: number,
    customerId: number,
    createdTimestamp: string,
    status: string,
    priority: number
}