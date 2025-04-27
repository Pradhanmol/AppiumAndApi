package org.fos.api.models.requestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class BulkVisitRequest {
    private String allocation_month;
    private String company_id;
    private List<Loan> loans;
    private String visit_date;

    // Nested Loan class
    @Getter
    @Setter
    @AllArgsConstructor
    public static class Loan {
        private String loan_id;
        private String applicant_name;
        private String comment;
        private String loan_status;
    }
}