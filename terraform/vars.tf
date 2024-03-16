variable "projectName" {
  default = "bluesburguer"
}

variable "tags" {
  type = map(string)
  default = {
    App      = "bluesburguer",
    Ambiente = "Desenvolvimento"
  }
}

variable "TF_VAR_AWS_ACCESS_KEY_ID" {
  description = "Access Key Id"
  type = string
}

variable "AWS_SECRET_ACCESS_KEY" {
  description = "Access Key Secret"
  type = string
}

variable "AWS_REGION" {
  description = "Aws Region"
  default = "us-east-1"
  type = string
}

variable "AWS_ACCOUNT_ID" {
  description = "Aws Account Id"
  type = string
}