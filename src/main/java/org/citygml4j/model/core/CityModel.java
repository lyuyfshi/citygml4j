package org.citygml4j.model.core;

import org.citygml4j.model.appearance.AppearanceProperty;
import org.citygml4j.model.versioning.VersionProperty;
import org.citygml4j.model.versioning.VersionTransitionProperty;
import org.xmlobjects.gml.model.common.ChildList;
import org.xmlobjects.gml.model.feature.AbstractFeature;
import org.xmlobjects.gml.model.feature.FeatureProperty;

import java.util.List;

public class CityModel extends AbstractFeatureWithLifespan {
    private List<AbstractCityObjectProperty> cityObjectMembers;
    private List<AppearanceProperty> appearanceMembers;
    private List<FeatureProperty<AbstractFeature>> featureMembers;
    private List<VersionProperty> versionMembers;
    private List<VersionTransitionProperty> versionTransitionMembers;
    private List<ADEPropertyOfCityModel> adeProperties;

    public List<AbstractCityObjectProperty> getCityObjectMembers() {
        if (cityObjectMembers == null)
            cityObjectMembers = new ChildList<>(this);

        return cityObjectMembers;
    }

    public void setCityObjectMembers(List<AbstractCityObjectProperty> cityObjectMembers) {
        this.cityObjectMembers = asChild(cityObjectMembers);
    }

    public List<AppearanceProperty> getAppearanceMembers() {
        if (appearanceMembers == null)
            appearanceMembers = new ChildList<>(this);

        return appearanceMembers;
    }

    public void setAppearanceMembers(List<AppearanceProperty> appearanceMembers) {
        this.appearanceMembers = asChild(appearanceMembers);
    }

    public List<FeatureProperty<AbstractFeature>> getFeatureMembers() {
        if (featureMembers == null)
            featureMembers = new ChildList<>(this);

        return featureMembers;
    }

    public void setFeatureMembers(List<FeatureProperty<AbstractFeature>> featureMembers) {
        this.featureMembers = asChild(featureMembers);
    }

    public List<VersionProperty> getVersionMembers() {
        if (versionMembers == null)
            versionMembers = new ChildList<>(this);

        return versionMembers;
    }

    public void setVersionMembers(List<VersionProperty> versionMembers) {
        this.versionMembers = asChild(versionMembers);
    }

    public List<VersionTransitionProperty> getVersionTransitionMembers() {
        if (versionTransitionMembers == null)
            versionTransitionMembers = new ChildList<>(this);

        return versionTransitionMembers;
    }

    public void setVersionTransitionMembers(List<VersionTransitionProperty> versionTransitionMembers) {
        this.versionTransitionMembers = asChild(versionTransitionMembers);
    }

    public List<ADEPropertyOfCityModel> getAdeProperties() {
        if (adeProperties == null)
            adeProperties = new ChildList<>(this);

        return adeProperties;
    }

    public void setAdeProperties(List<ADEPropertyOfCityModel> adeProperties) {
        this.adeProperties = asChild(adeProperties);
    }
}