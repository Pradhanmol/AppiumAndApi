package org.fos.api.models.requestDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuickVisitRequestDto {
        private String loan_id;
        private String visit_id;
        private String visit_purpose;
        private String is_customer_met;
        private String is_recovery_done;
        private String is_visit_done;
        private String payment_details;
        private String payment_method;
        private String payment_document_ids;
        private String pan_form_60_document_ids;
        private String amount_recovered;
        private String company_id;
        private String allocation_month;
        private String address_type;
        private String marked_location;
        private String address_location;
        private String agent_marked_status;
        private String reminder_id;
        private String address;
        private String applicant_name;
        private String comment;
        private String payment_collection_mode;
        private int address_index;
        private int battery_level;
        private String pan_card_number;
        private String inbound_bot_id;
        private String override_reason;
}
