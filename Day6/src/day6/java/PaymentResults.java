package day6;

import day6.SealedtypesQ02.Approved;
import day6.SealedtypesQ02.Declined;
import day6.SealedtypesQ02.Pending;

sealed interface PaymentResult permits Approved, Declined, Pending {
}
