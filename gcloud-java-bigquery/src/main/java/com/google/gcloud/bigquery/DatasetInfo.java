/*
 * Copyright 2015 Google Inc. All Rights Reserved.
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

package com.google.gcloud.bigquery;

import static com.google.common.base.MoreObjects.firstNonNull;
import static com.google.common.base.Preconditions.checkNotNull;

import com.google.api.client.util.Data;
import com.google.api.services.bigquery.model.Dataset;
import com.google.api.services.bigquery.model.TableReference;
import com.google.common.base.Function;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * Google BigQuery Dataset information. A dataset is a grouping mechanism that holds zero or more
 * tables. Datasets are the lowest level unit of access control; you cannot control access at the
 * table level.
 *
 * @see <a href="https://cloud.google.com/bigquery/docs/managing_jobs_datasets_projects#datasets">
 * Managing Jobs, Datasets, and Projects</a>
 */
public class DatasetInfo implements Serializable {

  static final Function<Dataset, DatasetInfo> FROM_PB_FUNCTION =
      new Function<Dataset, DatasetInfo>() {
        @Override
        public DatasetInfo apply(Dataset pb) {
          return DatasetInfo.fromPb(pb);
        }
      };
  static final Function<DatasetInfo, Dataset> TO_PB_FUNCTION =
      new Function<DatasetInfo, Dataset>() {
        @Override
        public Dataset apply(DatasetInfo datasetInfo) {
          return datasetInfo.toPb();
        }
      };

  private static final long serialVersionUID = -6615133444520365839L;

  private final DatasetId datasetId;
  private final List<Acl> acl;
  private final Long creationTime;
  private final Long defaultTableLifetime;
  private final String description;
  private final String etag;
  private final String friendlyName;
  private final String id;
  private final Long lastModified;
  private final String location;
  private final String selfLink;

  /**
   * A builder for {@code DatasetInfo} objects.
   */
  public abstract static class Builder {

    /**
     * Sets the dataset identity.
     */
    public abstract Builder datasetId(DatasetId datasetId);

    /**
     * Sets the dataset's access control configuration.
     *
     * @see <a href="https://cloud.google.com/bigquery/access-control">Access Control</a>
     */
    public abstract Builder acl(List<Acl> acl);

    abstract Builder creationTime(Long creationTime);

    /**
     * Sets the default lifetime of all tables in the dataset, in milliseconds. The minimum value is
     * 3600000 milliseconds (one hour). Once this property is set, all newly-created tables in the
     * dataset will have an expirationTime property set to the creation time plus the value in this
     * property, and changing the value will only affect new tables, not existing ones. When the
     * expirationTime for a given table is reached, that table will be deleted automatically. If a
     * table's expirationTime is modified or removed before the table expires, or if you provide an
     * explicit expirationTime when creating a table, that value takes precedence over the default
     * expiration time indicated by this property. This property is experimental and might be
     * subject to change or removed.
     */
    public abstract Builder defaultTableLifetime(Long defaultTableLifetime);

    /**
     * Sets a user-friendly description for the dataset.
     */
    public abstract Builder description(String description);

    abstract Builder etag(String etag);

    /**
     * Sets a user-friendly name for the dataset.
     */
    public abstract Builder friendlyName(String friendlyName);

    abstract Builder id(String id);

    abstract Builder lastModified(Long lastModified);

    /**
     * Sets the geographic location where the dataset should reside. This property is experimental
     * and might be subject to change or removed.
     *
     * @see <a href="https://cloud.google.com/bigquery/docs/reference/v2/datasets#location">Dataset
     *     Location</a>
     */
    public abstract Builder location(String location);

    abstract Builder selfLink(String selfLink);

    /**
     * Creates a {@code DatasetInfo} object.
     */
    public abstract DatasetInfo build();
  }

  static final class BuilderImpl extends Builder {

    private DatasetId datasetId;
    private List<Acl> acl;
    private Long creationTime;
    private Long defaultTableLifetime;
    private String description;
    private String etag;
    private String friendlyName;
    private String id;
    private Long lastModified;
    private String location;
    private String selfLink;

    BuilderImpl() {}

    BuilderImpl(DatasetInfo datasetInfo) {
      this.datasetId = datasetInfo.datasetId;
      this.acl = datasetInfo.acl;
      this.creationTime = datasetInfo.creationTime;
      this.defaultTableLifetime = datasetInfo.defaultTableLifetime;
      this.description = datasetInfo.description;
      this.etag = datasetInfo.etag;
      this.friendlyName = datasetInfo.friendlyName;
      this.id = datasetInfo.id;
      this.lastModified = datasetInfo.lastModified;
      this.location = datasetInfo.location;
      this.selfLink = datasetInfo.selfLink;
    }

    BuilderImpl(com.google.api.services.bigquery.model.Dataset datasetPb) {
      if (datasetPb.getDatasetReference() != null) {
        this.datasetId = DatasetId.fromPb(datasetPb.getDatasetReference());
      }
      if (datasetPb.getAccess() != null) {
        this.acl = Lists.transform(datasetPb.getAccess(), new Function<Dataset.Access, Acl>() {
          @Override
          public Acl apply(Dataset.Access accessPb) {
            return Acl.fromPb(accessPb);
          }
        });
      }
      this.creationTime = datasetPb.getCreationTime();
      this.defaultTableLifetime = datasetPb.getDefaultTableExpirationMs();
      this.description = datasetPb.getDescription();
      this.etag = datasetPb.getEtag();
      this.friendlyName = datasetPb.getFriendlyName();
      this.id = datasetPb.getId();
      this.lastModified = datasetPb.getLastModifiedTime();
      this.location = datasetPb.getLocation();
      this.selfLink = datasetPb.getSelfLink();
    }

    @Override
    public Builder datasetId(DatasetId datasetId) {
      this.datasetId = checkNotNull(datasetId);
      return this;
    }

    @Override
    public Builder acl(List<Acl> acl) {
      this.acl = acl != null ? ImmutableList.copyOf(acl) : null;
      return this;
    }

    @Override
    Builder creationTime(Long creationTime) {
      this.creationTime = creationTime;
      return this;
    }

    @Override
    public Builder defaultTableLifetime(Long defaultTableLifetime) {
      this.defaultTableLifetime =
          firstNonNull(defaultTableLifetime, Data.<Long>nullOf(Long.class));
      return this;
    }

    @Override
    public Builder description(String description) {
      this.description = firstNonNull(description, Data.<String>nullOf(String.class));
      return this;
    }

    @Override
    Builder etag(String etag) {
      this.etag = etag;
      return this;
    }

    @Override
    public Builder friendlyName(String friendlyName) {
      this.friendlyName = firstNonNull(friendlyName, Data.<String>nullOf(String.class));
      return this;
    }

    @Override
    Builder id(String id) {
      this.id = id;
      return this;
    }

    @Override
    Builder lastModified(Long lastModified) {
      this.lastModified = lastModified;
      return this;
    }

    @Override
    public Builder location(String location) {
      this.location = firstNonNull(location, Data.<String>nullOf(String.class));
      return this;
    }

    @Override
    Builder selfLink(String selfLink) {
      this.selfLink = selfLink;
      return this;
    }

    @Override
    public DatasetInfo build() {
      return new DatasetInfo(this);
    }
  }

  DatasetInfo(BuilderImpl builder) {
    datasetId = checkNotNull(builder.datasetId);
    acl = builder.acl;
    creationTime = builder.creationTime;
    defaultTableLifetime = builder.defaultTableLifetime;
    description = builder.description;
    etag = builder.etag;
    friendlyName = builder.friendlyName;
    id = builder.id;
    lastModified = builder.lastModified;
    location = builder.location;
    selfLink = builder.selfLink;
  }

  /**
   * Returns the dataset identity.
   */
  public DatasetId datasetId() {
    return datasetId;
  }

  /**
   * Returns the dataset's access control configuration.
   *
   * @see <a href="https://cloud.google.com/bigquery/access-control">Access Control</a>
   */
  public List<Acl> acl() {
    return acl;
  }

  /**
   * Returns the time when this dataset was created, in milliseconds since the epoch.
   */
  public Long creationTime() {
    return creationTime;
  }

  /**
   * Returns the default lifetime of all tables in the dataset, in milliseconds. Once this property
   * is set, all newly-created tables in the dataset will have an expirationTime property set to the
   * creation time plus the value in this property, and changing the value will only affect new
   * tables, not existing ones. When the expirationTime for a given table is reached, that table
   * will be deleted automatically. If a table's expirationTime is modified or removed before the
   * table expires, or if you provide an explicit expirationTime when creating a table, that value
   * takes precedence over the default expiration time indicated by this property.
   */
  public Long defaultTableLifetime() {
    return defaultTableLifetime;
  }

  /**
   * Returns a user-friendly description for the dataset.
   */
  public String description() {
    return description;
  }

  /**
   * Returns the hash of the dataset resource.
   */
  public String etag() {
    return etag;
  }

  /**
   * Returns a user-friendly name for the dataset.
   */
  public String friendlyName() {
    return friendlyName;
  }

  /**
   * Returns an opaque id for the dataset.
   */
  public String id() {
    return id;
  }

  /**
   * Returns the time when this dataset or any of its tables was last modified, in milliseconds
   * since the epoch.
   */
  public Long lastModified() {
    return lastModified;
  }

  /**
   * Returns the geographic location where the dataset should reside.
   *
   * @see <a href="https://cloud.google.com/bigquery/docs/managing_jobs_datasets_projects#dataset-location">
   *     Dataset Location</a>
   */
  public String location() {
    return location;
  }

  /**
   * Returns an URL that can be used to access the resource again. The returned URL can be used for
   * get or update requests.
   */
  public String selfLink() {
    return selfLink;
  }

  /**
   * Returns a builder for the dataset object.
   */
  public Builder toBuilder() {
    return new BuilderImpl(this);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("datasetId", datasetId)
        .add("creationTime", creationTime)
        .add("defaultTableLifetime", defaultTableLifetime)
        .add("description", description)
        .add("etag", etag)
        .add("friendlyName", friendlyName)
        .add("id", id)
        .add("lastModified", lastModified)
        .add("location", location)
        .add("selfLink", selfLink)
        .add("acl", acl)
        .toString();
  }

  @Override
  public int hashCode() {
    return Objects.hash(datasetId);
  }

  @Override
  public boolean equals(Object obj) {
    return obj.getClass().equals(DatasetInfo.class)
        && Objects.equals(toPb(), ((DatasetInfo) obj).toPb());
  }

  DatasetInfo setProjectId(String projectId) {
    Builder builder = toBuilder();
    builder.datasetId(datasetId().setProjectId(projectId));
    if (acl() != null) {
      List<Acl> acls = Lists.newArrayListWithCapacity(acl().size());
      for (Acl acl : acl()) {
        if (acl.entity().type() == Acl.Entity.Type.VIEW) {
          Dataset.Access accessPb = acl.toPb();
          TableReference viewReferencePb = accessPb.getView();
          if (viewReferencePb.getProjectId() == null) {
            viewReferencePb.setProjectId(projectId);
          }
          acls.add(Acl.of(new Acl.View(TableId.fromPb(viewReferencePb))));
        } else {
          acls.add(acl);
        }
      }
      builder.acl(acls);
    }
    return builder.build();
  }

  Dataset toPb() {
    Dataset datasetPb = new Dataset();
    datasetPb.setDatasetReference(datasetId.toPb());
    datasetPb.setCreationTime(creationTime);
    datasetPb.setDefaultTableExpirationMs(defaultTableLifetime);
    datasetPb.setDescription(description);
    datasetPb.setEtag(etag);
    datasetPb.setFriendlyName(friendlyName);
    datasetPb.setId(id);
    datasetPb.setLastModifiedTime(lastModified);
    datasetPb.setLocation(location);
    datasetPb.setSelfLink(selfLink);
    if (acl != null) {
      datasetPb.setAccess(Lists.transform(acl, new Function<Acl, Dataset.Access>() {
        @Override
        public Dataset.Access apply(Acl acl) {
          return acl.toPb();
        }
      }));
    }
    return datasetPb;
  }

  /**
   * Returns a builder for a {@code DatasetInfo} object given it's identity.
   */
  public static Builder builder(DatasetId datasetId) {
    return new BuilderImpl().datasetId(datasetId);
  }

  /**
   * Returns a builder for a {@code DatasetInfo} object given it's user-defined id.
   */
  public static Builder builder(String datasetId) {
    return builder(DatasetId.of(datasetId));
  }

  /**
   * Returns a builder for the DatasetInfo object given it's user-defined project and dataset ids.
   */
  public static Builder builder(String projectId, String datasetId) {
    return builder(DatasetId.of(projectId, datasetId));
  }

  static DatasetInfo fromPb(Dataset datasetPb) {
    return new BuilderImpl(datasetPb).build();
  }
}