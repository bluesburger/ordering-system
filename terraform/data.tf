data "aws_iam_access_keys" "terraform" {
  user = "github"
}

data "github_actions_environment_secrets" "aws_rsd_password" {
    name        = "ordering-system"
    environment = "AWS_RDS_PASSWORD"
}