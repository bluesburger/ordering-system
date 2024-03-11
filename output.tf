output "rds_endpoint" {
  value     = aws_db_instance.rds.endpoint
  # sensitive = true
}

output "rds_aws_instance_identifier" {
  value     = aws_db_instance.rds.identifier
  sensitive = true
}