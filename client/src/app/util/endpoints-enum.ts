

export enum Endpoint {
  LOGIN = "http://localhost:8084/auth/login/",
  PHARMACY_LIST = "http://localhost:8084/pharmacies/",
  USER_PROFILE = "http://localhost:8084/users/profile",
  USER_UPDATE = "http://localhost:8084/users/update",

  MED_UPDATE = "http://localhost:8084/meds/update/",


  PHARMACY_MED_LIST = "http://localhost:8084/pharmacies/meds/",
  MED_LIST = "http://localhost:8084/meds",
  MED_PHARMACY_LIST = "http://localhost:8084/meds/pharmacies/",
  USER_MED_LIST = "http://localhost:8084/users/meds/",
  DERM_APPOINTMENT_LIST = "http://localhost:8084/dermAppointments/",
  USER_ADD_ALLERGY = "http://localhost:8084/users/addAllergy",
  USER_ADD_DERM_APPOINTMENT = "http://localhost:8084/users/addDermAppointment/",
  SEND_EMAIL = "http://localhost:8084/email/send",
  RESERVE_MED = "http://localhost:8084/pharmacyMed/reserve/",

  FUTURE_DERM_APPOINTMENT_LIST = "http://localhost:8084/dermAppointments/futureAppointments",
  PAST_DERM_APPOINTMENT_LIST = "http://localhost:8084/dermAppointments/pastAppointments",
  FREE_SCHEDULED_DERM_APPOINTMENT = "http://localhost:8084/dermAppointments/frees/",

  USER_RESERVATIONS = "http://localhost:8084/users/reservations",
  FREE_RESERVED_MED = "http://localhost:8084/reservations/free/",

  PHARMACY_PHARMACIST_LIST = "http://localhost:8084/pharmacies/pharmacists/",
    PICK_UP_MED = "http://localhost:8084/reservations/pickUpMed/",
    FREE_PHARMACIES = "http://localhost:8084/terms/getAllFreePharmacies",
    FREE_PHARMACISTS = "http://localhost:8084/terms/getAllFreePharmacists",
    SCHEDULE_PHARMACIST = "http://localhost:8084/terms/add/",
    FUTURE_PHARM_APPOINTMENT_LIST = "http://localhost:8084/terms/futureTerms",
    PAST_PHARM_APPOINTMENT_LIST = "http://localhost:8084/terms/pastTerms",
    FREE_SCHEDULED_PHARM_APPOINTMENT = "http://localhost:8084/terms/frees/",
    USER_PRESCRIPTIONS = "http://localhost:8084/users/prescriptions",
    RATE_DERMATOLOGIST = "http://localhost:8084/rates/rateDermatologist/",
    DERMATOLOGIST_LIST = "http://localhost:8084/users/dermatologists",
    PHARMACIST_LIST = "http://localhost:8084/users/pharmacists",
    MEDS_RATE_LIST = "http://localhost:8084/reservations/medications",
    PHARMACY_RATE_LIST = "http://localhost:8084/pharmacies/pharmaciesToRate",
    RATE_PHARMACIST = "http://localhost:8084/rates/ratePharmacist/",
    RATE_MED = "http://localhost:8084/rates/rateMed/",
    RATE_PHARMACY = "http://localhost:8084/rates/ratePharmacy/",
    RATE_LIST = "http://localhost:8084/rates",
    CHANGE_RATE = "http://localhost:8084/rates/changeRate/",
    RATED = "http://localhost:8084/rates/rated/",

  ADD_NEW_TERM = "http://localhost:8084/dermAppointments/add_appointment/",
  DELETE_MED = "http://localhost:8084/pharmacies/deleteMed/",
  ADD_MED = "http://localhost:8084/pharmacies/addMed/",
  
  ALL_PHARMACIST_LIST = "http://localhost:8084/users/allPharmacists/",

  ADD_PROMOTION = "http://localhost:8084/promotions/addPromotion/",
  GET_PROMOTIONS = "http://localhost:8084/promotions/getPromotions/",
  GET_SUBSCRIBED_USERS = "http://localhost:8084/promotions/getSubscribed/",
  GET_PHARMACY = "http://localhost:8084/pharmacies/getPharmacy/",

  FIND_ALL_PHARMACISTS = "http://localhost:8084/pharmacists/allPharmacists/",

  ADD_NEW_PHARMACIST = "http://localhost:8084/pharmacists/add_pharmacist/",

  DELETE_PHARMACIST = "http://localhost:8084/pharmacies/deletePharmacist/",

  FIND_ALL_DERMATOLOGISTS = "http://localhost:8084/dermatologists/allDermatologists/",

  ADD_NEW_DERMATOLOGIST = "http://localhost:8084/dermatologists/add_dermatologist/",

  DELETE_DERMATOLOGIST = "http://localhost:8084/pharmacies/deleteDermatologist/",

  FIND_ALL_VACATIONS = "http://localhost:8084/vacations/allVacation/",


  APPROVE = "http://localhost:8084/vacations/approve/",

  DECLINE = "http://localhost:8084/vacations/decline/",

  FIND_WORKER = "http://localhost:8084/vacations/getWorkerName/"

}