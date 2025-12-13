package fr.adriencaubel.tstoddd.infrastructure.web.dto;

public record CreateOrderRequestModel(Long articleId, int quantity, Long clientId) {}
