package org.smartregister.chw.tbleprosy_sample.utils;

public class SampleConstants {

    public static class CONFIGURATION {
        public static final String LOGIN = "login";
        public static final String FAMILY_REGISTER = "family_register";
        public static final String FAMILY_MEMBER_REGISTER = "family_member_register";

    }

    public static final class EventType {
        public static final String FAMILY_REGISTRATION = "Family Registration";
        public static final String FAMILY_MEMBER_REGISTRATION = "Family Member Registration";

        public static final String UPDATE_FAMILY_REGISTRATION = "Update Family Registration";
        public static final String UPDATE_FAMILY_MEMBER_REGISTRATION = "Update Family Member Registration";
    }

    public static class JSON_FORM {
        public static final String FAMILY_REGISTER = "family_register";
        public static final String FAMILY_MEMBER_REGISTER = "family_member_register";
    }

    public static class RELATIONSHIP {
        public static final String FAMILY = "family";
        public static final String FAMILY_HEAD = "family_head";
        public static final String PRIMARY_CAREGIVER = "primary_caregiver";

    }

    public static class TABLE_NAME {
        public static final String FAMILY = "ec_family";
        public static final String FAMILY_MEMBER = "ec_family_member";
    }

    public static class IDENTIFIER {
        public static final String UNIQUE_IDENTIFIER_KEY = "opensrp_id";
    }

}
