// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: google/cloud/automl/v1beta1/model_evaluation.proto

package com.google.cloud.automl.v1beta1;

public final class ModelEvaluationOuterClass {
  private ModelEvaluationOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_google_cloud_automl_v1beta1_ModelEvaluation_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_google_cloud_automl_v1beta1_ModelEvaluation_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n2google/cloud/automl/v1beta1/model_eval" +
      "uation.proto\022\033google.cloud.automl.v1beta" +
      "1\032\034google/api/annotations.proto\0320google/" +
      "cloud/automl/v1beta1/classification.prot" +
      "o\032-google/cloud/automl/v1beta1/translati" +
      "on.proto\032\037google/protobuf/timestamp.prot" +
      "o\"\350\002\n\017ModelEvaluation\022i\n!classification_" +
      "evaluation_metrics\030\010 \001(\0132<.google.cloud." +
      "automl.v1beta1.ClassificationEvaluationM" +
      "etricsH\000\022c\n\036translation_evaluation_metri",
      "cs\030\t \001(\01329.google.cloud.automl.v1beta1.T" +
      "ranslationEvaluationMetricsH\000\022\014\n\004name\030\001 " +
      "\001(\t\022\032\n\022annotation_spec_id\030\002 \001(\t\022/\n\013creat" +
      "e_time\030\005 \001(\0132\032.google.protobuf.Timestamp" +
      "\022\037\n\027evaluated_example_count\030\006 \001(\005B\t\n\007met" +
      "ricsBf\n\037com.google.cloud.automl.v1beta1P" +
      "\001ZAgoogle.golang.org/genproto/googleapis" +
      "/cloud/automl/v1beta1;automlb\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.google.api.AnnotationsProto.getDescriptor(),
          com.google.cloud.automl.v1beta1.ClassificationProto.getDescriptor(),
          com.google.cloud.automl.v1beta1.TranslationProto.getDescriptor(),
          com.google.protobuf.TimestampProto.getDescriptor(),
        }, assigner);
    internal_static_google_cloud_automl_v1beta1_ModelEvaluation_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_google_cloud_automl_v1beta1_ModelEvaluation_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_google_cloud_automl_v1beta1_ModelEvaluation_descriptor,
        new java.lang.String[] { "ClassificationEvaluationMetrics", "TranslationEvaluationMetrics", "Name", "AnnotationSpecId", "CreateTime", "EvaluatedExampleCount", "Metrics", });
    com.google.api.AnnotationsProto.getDescriptor();
    com.google.cloud.automl.v1beta1.ClassificationProto.getDescriptor();
    com.google.cloud.automl.v1beta1.TranslationProto.getDescriptor();
    com.google.protobuf.TimestampProto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
