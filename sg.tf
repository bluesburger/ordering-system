resource "aws_security_group" "sg-rds" {
  name        = "SG-${var.projectName}-DATABASE"
  description = var.projectName
  vpc_id      = var.vpcId

  ingress {
    description = "VPC"
    from_port   = 3306
    to_port     = 3306
    protocol    = "tcp"
    # cidr_blocks = [var.vpcCIDR]
    cidr_blocks = ["0.0.0.0/0"]
  }

  egress {
    description = "VPC"
    from_port   = 3306
    to_port     = 3306
    protocol    = "tcp"
    # cidr_blocks = [var.vpcCIDR]
    cidr_blocks = ["0.0.0.0/0"]
  }
}

# TODO: descomentar as linhas 11 e 20, e comentar as linhas 12 e 21 para bloquear acessos externos, 
# e restringir os acesso apenas para dentro do cluster do ECS