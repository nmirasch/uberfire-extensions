/*
 * Copyright 2015 JBoss, by Red Hat, Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.uberfire.ext.plugin.model;

import java.util.Collection;

import org.jboss.errai.common.client.api.annotations.Portable;
import org.uberfire.backend.vfs.Path;

@Portable
public class DynamicMenu extends Plugin {

    private Collection<DynamicMenuItem> menuItems;

    public DynamicMenu() {
    }

    public DynamicMenu( final String name,
                        final PluginType type,
                        final Path path,
                        final Collection<DynamicMenuItem> menuItems ) {
        super( name, type, path );
        this.menuItems = menuItems;
    }

    public Collection<DynamicMenuItem> getMenuItems() {
        return menuItems;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) {
            return true;
        }
        if ( !( o instanceof DynamicMenu ) ) {
            return false;
        }
        if ( !super.equals( o ) ) {
            return false;
        }

        DynamicMenu that = (DynamicMenu) o;

        if ( menuItems != null ? !menuItems.equals( that.menuItems ) : that.menuItems != null ) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = ~~result;
        result = 31 * result + ( menuItems != null ? menuItems.hashCode() : 0 );
        result = ~~result;
        return result;
    }
}
