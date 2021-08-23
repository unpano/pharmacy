
export enum Endpoint {
  LOGIN = "http://localhost:8084/auth/login/",
  PHARMACY_LIST = "http://localhost:8084/pharmacies/",
  USER_PROFILE = "http://localhost:8084/users/profile",
  USER_UPDATE = "http://localhost:8084/users/update",
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
  FREE_RESERVED_MED = "http://localhost:8084/reservations/free/"
}