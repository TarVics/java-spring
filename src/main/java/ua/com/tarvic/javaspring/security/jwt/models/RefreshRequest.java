package ua.com.tarvic.javaspring.security.jwt.models;
///+++++++++++++ JWT PAIR
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RefreshRequest {
    private String refreshToken;
}
///+++++++++++++ JWT PAIR