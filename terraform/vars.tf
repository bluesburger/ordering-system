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