package uz.pdp.projectutils.dto;

public record AuthenticationDto(
        String accessToken,
        String refreshToken
) {
}
