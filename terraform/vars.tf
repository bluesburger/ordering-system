variable "projectName" {
  default = "bluesburguer"
}

variable "clusterName" {
  default = "BluesBurguer"
}

variable "regionDefault" {
  default = "us-east-1"
}

variable "vpcCIDR" {
  default = "172.31.0.0/20"
}

variable "rdsUser" {
  description = "Inserir usuario do banco em secrets"
  default     = "root"
}

variable "rdsPass" {
  description = "Inserir senha do banco em secrets"
  default     = "Root2024"
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

variable "access_key" {
  description = "Qual a access_key a ser utilizada?"
}

variable "secret_key" {
  description = "Qual a secret_key a ser utilizada?"
}
