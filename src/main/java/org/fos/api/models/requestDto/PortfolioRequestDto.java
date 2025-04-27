package org.fos.api.models.requestDto;

import lombok.*;

@Getter
@Setter
@Builder
public class PortfolioRequestDto {
        private Coordinates current_coordinates;
        private String company_id;

    // Additional fields for query parameters
    private String companyType;
    private String allocationMonth;
    private int pageNumber;
    private int pageSize;
    private String agentEmail;
    private String sort;
        @Getter
        @Setter
        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        @Builder
        public static class Coordinates{
            private double latitude;
            private double longitude;
        }
}
