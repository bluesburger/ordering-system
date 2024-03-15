variable "projectName" {
  default = "bluesburguer"
}

variable "regionDefault" {
  default = "us-east-1"
}

variable "tags" {
  type = map(string)
  default = {
    App      = "bluesburguer",
    Ambiente = "Desenvolvimento"
  }
}

# Define a variável com o ID da conta da AWS
variable "aws_account_id" {
  default = "637423186279"
}

# Define a variável com a região da AWS
variable "aws_region" {
  default = "us-east-1"
}

variable "AWS_ACCESS_KEY_ID" {
  default = ""
  type = string
}

variable "AWS_SECRET_ACCESS_KEY" {
  default = ""
  type = string
}

variable "AWS_REGION" {
  default = "us-east-1"
  type = string
}

variable "AWS_ACCOUNT_ID" {
  default = ""
  type = string
}