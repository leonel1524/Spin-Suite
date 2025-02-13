/*************************************************************************************
 * Product: Spin-Suite (Mobile Suite)                       		                 *
 * Copyright (C) 2012-2018 E.R.P. Consultores y Asociados, C.A.                      *
 * Contributor(s): Yamel Senih ysenih@erpya.com				  		                 *
 * This program is free software: you can redistribute it and/or modify              *
 * it under the terms of the GNU General Public License as published by              *
 * the Free Software Foundation, either version 3 of the License, or                 *
 * (at your option) any later version.                                               *
 * This program is distributed in the hope that it will be useful,                   *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of                    *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the                     *
 * GNU General Public License for more details.                                      *
 * You should have received a copy of the GNU General Public License                 *
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.            *
 ************************************************************************************/
package org.erpya.security.ui.reset;

import android.support.annotation.Nullable;

/**
 * Authentication result : success (user details) or error message.
 */
class ResetResult {
    @Nullable
    private ResetUserView success;
    @Nullable
    private Integer error;
    @Nullable
    private String errorMessage;

    ResetResult(@Nullable Integer error, String errorMessage) {
        this.error = error;
        this.errorMessage = errorMessage;
    }

    ResetResult(@Nullable ResetUserView success) {
        this.success = success;
    }

    @Nullable
    ResetUserView getSuccess() {
        return success;
    }

    @Nullable
    Integer getError() {
        return error;
    }

    @Nullable
    String getErrorMessage() {
        return errorMessage;
    }
}
