terraform {

  required_providers {

    aws = {
      source  = "hashicorp/aws"
      version = "~> 5.0"
    }
  }

  backend "s3" {
    bucket = "ordering-system"
    key    = "bluesburguer/application.tfstate"
    region = "us-east-1"
  }
}

provider "aws" {
  region     = var.TF_VAR_AWS_REGION
  access_key = var.TF_VAR_AWS_ACCESS_KEY_ID
  secret_key = var.TF_VAR_AWS_SECRET_ACCESS_KEY

  default_tags {
    tags = var.tags
  }
}