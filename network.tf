resource "aws_db_subnet_group" "subnet-rds" {
  name       = var.projectName
  subnet_ids = [var.subnet01, var.subnet02, var.subnet03]
}