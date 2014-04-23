/**
 * Copyright (C) 2014 Evil-Co <http://wwww.evil-co.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.evilco.license.common.data;

import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * Allows (de-)serialization of license holders.
 * @author			Johannes "Akkarin" Donath <johannesd@evil-co.com>
 * @copyright			Copyright (C) 2014 Evil-Co <http://www.evil-co.com>
 */
public class LicenseHolderJsonAdater implements JsonDeserializer<ILicenseHolder> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ILicenseHolder deserialize (JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		if (json.getAsJsonObject ().has ("companyName"))
			return context.deserialize (json, CompanyLicenseHolder.class);
		else
			return context.deserialize (json, PersonLicenseHolder.class);
	}
}