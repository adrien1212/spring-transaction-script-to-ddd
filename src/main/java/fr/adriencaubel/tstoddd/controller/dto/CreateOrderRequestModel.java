package fr.adriencaubel.tstoddd.controller.dto;

public record CreateOrderRequestModel(Long articleId, int quantity, Long clientId) {}
